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

export class Post {
  id: string;
  date: string;
  msg: string;

  constructor(id: string, date: string, msg: string) {
    this.id = id;
    this.date = date;
    this.msg = msg;
  }
}
