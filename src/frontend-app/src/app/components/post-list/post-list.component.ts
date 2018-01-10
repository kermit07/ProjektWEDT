import {Component, OnDestroy, OnInit} from '@angular/core';
import {Post} from "../post/post.component";
import {RestService} from "../../service/RestService";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit, OnDestroy {

  limit = 20;
  offset = 0;
  posts: Post[];
  private sub:Subscription;

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.posts = [];
    this.sub = this.restService.getPosts(this.limit, 0)
      .subscribe((data) => {
        this.posts = data;
      });
  }

  loadMore() {
    this.offset = this.posts.length;
    this.sub = this.restService.getPosts(this.limit, this.offset)
      .subscribe((data) => {
        this.posts = this.posts.concat(data);
      });
  }

  loadAll() {
    this.offset = this.posts.length;
    this.sub = this.restService.getPosts(Number.MAX_SAFE_INTEGER, this.offset)
      .subscribe((data) => {
        this.posts = this.posts.concat(data);
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
