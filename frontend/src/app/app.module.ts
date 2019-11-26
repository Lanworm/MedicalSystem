import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {EventsComponent} from './events/events.component';
import {EventListComponent} from './events/event-list/event-list.component';
import {EventFormComponent} from './events/event-form/event-form.component';
import {ReactiveFormsModule} from '@angular/forms';
import {DialogComponent} from './services/dialog/dialog/dialog.component';
import {NotFoundComponent} from './404/not-found/not-found.component';
import {RouterModule, Routes} from '@angular/router';
import {MenuComponent} from './menu/menu.component';
import {LoginComponent} from './login/login.component';
import {DashboardsComponent} from './dashboards/dashboards.component';
import {HttpErrorInterceptor} from './utils/http-error.interceptor';
import {ErrorDialogComponent} from './error-dialog/error-dialog.component';
import {AuthGuard} from './services/auth/auth.guard';
import {PatientsComponent} from './patients/patients.component';
import {CardComponent} from './patients/card/card.component';

const routes: Routes = [

  {
    path: 'dashboard',
    component: DashboardsComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'events',
        component: EventsComponent,
        canActivate: [AuthGuard]
      },
      {
        path: 'patients',
        component: PatientsComponent,
        canActivate: [AuthGuard],

      },
    ]
  },
  {
    path: 'card/:patientId',
    component: CardComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: '',
    redirectTo: 'dashboard/events',
    pathMatch: 'full'
  },
  {
    path: '**',
    component: NotFoundComponent
  },

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
    LoginComponent,
    DashboardsComponent,
    ErrorDialogComponent,
    PatientsComponent,
    CardComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpErrorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
