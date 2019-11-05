import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {DoctorsListComponent} from './doctors-list/doctors-list.component';
import {DoctorCardComponent} from './doctors-list/doctor-card/doctor-card.component';
import {HttpClientModule} from '@angular/common/http';
import { EventsComponent } from './events/events.component';
import { EventListComponent } from './events/event-list/event-list.component';
import {DataTablesModule} from 'angular-datatables';

@NgModule({
  declarations: [
    AppComponent,
    DoctorsListComponent,
    DoctorCardComponent,
    EventsComponent,
    EventListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    DataTablesModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
