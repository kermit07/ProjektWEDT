import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  @Input() post: Post;

  constructor() {
  }

  ngOnInit() {
  }
}

export class PostResult {
  key: Post;
  value: number;

  constructor(key: Post, value: number) {
    this.key = key;
    this.value = value;
  }
}

export class Post {
  id: string;
  msg: string;
  date: string;

  constructor(id: string, msg: string, date: string) {
    this.id = id;
    this.msg = msg;
    this.date = date;
  }
}
