import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class RestService {

  constructor(public http: HttpClient) {
  }

  getPosts(limit: number, offset: number) {
    return this.http.get("http://localhost:8080/api/posts?limit=" + limit + "&offset=" + offset)
      .map(res => {
        const items = <any[]>res;
        items.forEach(item => item.date = this.dateToString(item.date));
        return items;
      });
  }

  getPost(id: String) {
    return this.http.get("http://localhost:8080/api/post/" + id).map(res => {
      const item = <any>res;
      item.post.date = this.dateToString(item.post.date);
      return item;
    });
  }

  run(id: String) {
    return this.http.get("http://localhost:8080/api/run/" + id + "?synonymEnabled=true")
      .map((res: any) => {
        res.map((post: any) => {
          post.post.date = this.dateToString(post.post.date);
        })
        return <any[]>res;
      });
  }

  dateToString(item: any): String {
    return item.year + "-" + (item.monthValue < 10 ? "0" : "") + item.monthValue + "-" + (item.dayOfMonth < 10 ? "0" : "") + item.dayOfMonth + " " + (item.hour < 10 ? "0" : "") + item.hour + ":" + (item.minute < 10 ? "0" : "") + item.minute + ":" + (item.second < 10 ? "0" : "") + item.second;
  }

}
