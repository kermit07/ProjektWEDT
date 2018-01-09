import { Component, OnInit } from '@angular/core';
import {Post} from "../post/post.component";

@Component({
  selector: 'app-post-selected',
  templateUrl: './post-selected.component.html',
  styleUrls: ['./post-selected.component.css']
})
export class PostSelectedComponent implements OnInit {
  selectedPost: Post;
  similarityPosts: Post[];

  constructor() { }

  ngOnInit() {
    this.selectedPost = new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)");
    this.similarityPosts = [];
  }

  simpleRun() {

    this.similarityPosts = [];
    this.similarityPosts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.similarityPosts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.similarityPosts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
  }

  advanceRun() {

    this.similarityPosts = [];
    this.similarityPosts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.similarityPosts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));

  }

}
