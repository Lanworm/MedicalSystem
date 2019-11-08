import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {EventsComponent} from './events/events.component';
import {EventListComponent} from './events/event-list/event-list.component';
import {DataTablesModule} from 'angular-datatables';
import {EventFormComponent} from './events/event-form/event-form.component';
import {ReactiveFormsModule} from '@angular/forms';
import { DialogComponent } from './shared/dialog/dialog/dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    EventsComponent,
    EventListComponent,
    EventFormComponent,
    DialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    DataTablesModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
