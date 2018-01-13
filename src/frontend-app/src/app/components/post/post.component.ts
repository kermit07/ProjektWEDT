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
  post: Post;
  similarity: any[];
  result: number;
  kind: string;


  constructor(post: Post, similarity: any[], result: number, kind: string) {
    this.post = post;
    this.similarity = similarity;
    this.result = result;
    this.kind = kind;
  }
}

export class Post {
  id: string;
  msg: string;
  date: string;
  keywords: string[];

  constructor(id: string, msg: string, date: string, keywords: string[]) {
    this.id = id;
    this.msg = msg;
    this.date = date;
    this.keywords = keywords;
  }
}
