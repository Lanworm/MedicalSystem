import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {EventsComponent} from './events/events.component';
import {EventListComponent} from './events/event-list/event-list.component';
import {DataTablesModule} from 'angular-datatables';
import {EventFormComponent} from './events/event-form/event-form.component';
import {ReactiveFormsModule} from '@angular/forms';
import {DialogComponent} from './shared/dialog/dialog/dialog.component';
import {NotFoundComponent} from './404/not-found/not-found.component';
import {RouterModule, Routes} from '@angular/router';
import { MenuComponent } from './menu/menu.component';

const routes: Routes = [
  {
    path: 'events',
    component: EventsComponent,

  },
  {
    path: '',
    redirectTo: '/events',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: NotFoundComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    EventsComponent,
    EventListComponent,
    EventFormComponent,
    DialogComponent,
    NotFoundComponent,
    MenuComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    DataTablesModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
