import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MainComponent } from './components/main/main.component';
import { PostListComponent } from './components/post-list/post-list.component';
import { PostSelectedComponent } from './components/post-selected/post-selected.component';
import {routing} from "./app.routing";
import { PostComponent } from './components/post/post.component';
import { PostSimpleComponent } from './components/post-simple/post-simple.component';


@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent,
    MainComponent,
    PostListComponent,
    PostSelectedComponent,
    PostComponent,
    PostSimpleComponent
  ],
  imports: [
    BrowserModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
