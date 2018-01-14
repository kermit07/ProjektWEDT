import {Component, OnDestroy, OnInit} from '@angular/core';
import {Post, PostResult} from "../post/post.component";
import {RestService} from "../../service/RestService";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";
import {isUndefined} from "util";

@Component({
  selector: 'app-post-selected',
  templateUrl: './post-selected.component.html',
  styleUrls: ['./post-selected.component.css']
})
export class PostSelectedComponent implements OnInit, OnDestroy {
  selectedPost: PostResult;
  synonymEnabled = false;        // czy szukamy synonimów
  dictEnabled = false;           // czy szukamy synonimów
  kindEnabled = false;           // czy szukamy synonimów
  limit;
  offset;
  similarityPosts: PostResult[];
  id: String
  private simpleLoading = false;
  private sub: Subscription;
  private sub1: Subscription;
  private sub2: Subscription;

  constructor(private route: ActivatedRoute, private restService: RestService) {
  }

  ngOnInit() {
    this.similarityPosts = [];
    this.selectedPost = new PostResult(new Post("", "", "", []), [], 0.0, "");
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.sub1 = this.restService.getPost(this.id).subscribe(data => {
        this.selectedPost = data;
      }
    );
  }

  simpleRun() {
    this.simpleLoading = true;
    this.similarityPosts = [];
    this.limit = 20;
    this.offset = 0;
    this.sub2 = this.restService.run(this.selectedPost.post.id, this.synonymEnabled, this.dictEnabled, this.kindEnabled, this.limit, this.offset)
      .subscribe((data) => {
        this.similarityPosts = data;
        this.simpleLoading = false;
      });
  }

  loadMore() {
    this.offset = this.similarityPosts.length;
    this.simpleLoading = true;
    this.sub2 = this.restService.run(this.selectedPost.post.id, this.synonymEnabled, this.dictEnabled, this.kindEnabled, this.limit, this.offset)
      .subscribe((data) => {
        this.similarityPosts = this.similarityPosts.concat(data);
        this.simpleLoading = false;
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
    this.sub1.unsubscribe();
    if (!isUndefined(this.sub2))
      this.sub2.unsubscribe();
  }

}
