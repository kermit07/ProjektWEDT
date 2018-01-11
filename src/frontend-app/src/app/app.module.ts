import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MainComponent } from './components/main/main.component';
import { PostListComponent } from './components/post-list/post-list.component';
import { PostSelectedComponent } from './components/post-selected/post-selected.component';
import {routing} from "./app.routing";
import { PostComponent } from './components/post/post.component';
import { PostResultComponent } from './components/post-result/post-result.component';
import {RestService} from "./service/RestService";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    MainComponent,
    PostListComponent,
    PostSelectedComponent,
    PostComponent,
    PostResultComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    routing
  ],
  providers: [
    RestService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
