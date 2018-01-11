import {Component, Input, OnInit} from '@angular/core';
import {Post, PostResult} from "../post/post.component";

@Component({
  selector: 'app-post-simple',
  templateUrl: './post-result.component.html',
  styleUrls: ['./post-result.component.css']
})
export class PostResultComponent implements OnInit {
  @Input() post: PostResult;

  constructor() { }

  ngOnInit() {
  }

}
