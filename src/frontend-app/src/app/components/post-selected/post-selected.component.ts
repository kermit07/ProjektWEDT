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
  selectedPost: Post;
  similarityPosts: PostResult[];
  id: String;
  private sub: Subscription;
  private sub1: Subscription;
  private sub2: Subscription;
  private sub3: Subscription;

  constructor(private route: ActivatedRoute, private restService: RestService) {
  }

  ngOnInit() {
    this.similarityPosts = [];
    this.selectedPost = new Post("", "", "");
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
    });
    this.sub1 = this.restService.getPost(this.id).subscribe(data => {
        this.selectedPost = data;
      }
    );
  }

  simpleRun() {
    this.similarityPosts = [];
    this.sub2 = this.restService.getSimple(this.selectedPost.id)
      .subscribe((data) => {
        this.similarityPosts = data;
        console.log(this.similarityPosts)
      });
  }

  advanceRun() {
    this.similarityPosts = [];
    this.sub3 = this.restService.getAdvanced(this.selectedPost.id)
      .subscribe((data) => {
        this.similarityPosts = data;
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
    this.sub1.unsubscribe();
    if (!isUndefined(this.sub2))
      this.sub2.unsubscribe();
    if (!isUndefined(this.sub3))
      this.sub3.unsubscribe();
  }

}
