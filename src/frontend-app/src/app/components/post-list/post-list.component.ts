import {Component, OnInit} from '@angular/core';
import {Post} from "../post/post.component";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {

  posts: Post[];

  constructor() {
  }

  ngOnInit() {
    this.posts = [];
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
    this.posts.push(new Post("1313123123-3123123", "2018-12-12 12:12:12", "Witam poszukujemy basisty do punkowegoreggowego zespołu :p dużo koncertów w planie wydanie płyt. Kapela nazywa się Zakaz Posiadania. Zapraszam na priv ;p a i jeszcze szukam sali prób w Szczecinie jakby mógł ktoś coś pomóc :)"));
  }

  loadMore() {
    // TODO
  }

  loadAll() {
    // TODO
  }

}
