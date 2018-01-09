import {RouterModule, Routes} from "@angular/router";
import {MainComponent} from "./components/main/main.component";
import {PostListComponent} from "./components/post-list/post-list.component";
import {PostSelectedComponent} from "./components/post-selected/post-selected.component";
import {NotFoundComponent} from "./components/not-found/not-found.component";

const appRoutes: Routes = [
  {path: '', component: MainComponent},
  {path: 'list', component: PostListComponent},
  {path: 'list/:id', component: PostSelectedComponent},
  {path: '**', component: NotFoundComponent}
];

export const routing = RouterModule.forRoot(appRoutes);
