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
      item.date = this.dateToString(item.date);
      return item;
    });
  }

  getSimple(id: String) {
    return this.http.get("http://localhost:8080/api/simple/" + id)
      .map((res: any) => {
        res.map((post: any) => {
          post.key.date = this.dateToString(post.key.date);
        })
        return <any[]>res;
      });
  }

  getAdvanced(id: String) {
    return this.http.get("http://localhost:8080/api/advanced/" + id)
      .map((res: any) => {
        res.map((post: any) => {
          post.key.date = this.dateToString(post.key.date);
        })
        return <any[]>res;
      });
  }

  dateToString(item: any): String {
    return item.year + "-" + (item.monthValue < 10 ? "0" : "") + item.monthValue + "-" + (item.dayOfMonth < 10 ? "0" : "") + item.dayOfMonth + " " + (item.hour < 10 ? "0" : "") + item.hour + ":" + (item.minute < 10 ? "0" : "") + item.minute + ":" + (item.second < 10 ? "0" : "") + item.second;
  }

}
