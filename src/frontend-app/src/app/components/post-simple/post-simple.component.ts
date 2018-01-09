import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../post/post.component";

@Component({
  selector: 'app-post-simple',
  templateUrl: './post-simple.component.html',
  styleUrls: ['./post-simple.component.css']
})
export class PostSimpleComponent implements OnInit {
  @Input() post: Post;

  constructor() { }

  ngOnInit() {
  }

}
