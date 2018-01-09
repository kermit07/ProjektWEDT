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

  posts: Post[];
  private sub:Subscription;

  constructor(private restService: RestService) {
  }

  ngOnInit() {
    this.posts = [];
    this.sub = this.restService.getPosts()
      .subscribe((data) => {
        this.posts = data;
      });
  }

  loadMore() {
    // TODO
  }

  loadAll() {
    // TODO
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
