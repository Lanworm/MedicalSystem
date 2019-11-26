import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {getQuerySearch} from '../../utils/url';

@Injectable({
  providedIn: 'root'
})
export class EventsService {
  constructor(private http: HttpClient) {

  }

  getAll(params): Observable<any> {
    return this.http.get('/api/events' + getQuerySearch(params));
  }

  addEvent(params): Observable<any> {
    return this.http.put('/api/events', params);
  }

  deleteEvent(id): Observable<any> {
    return this.http.delete('/api/events/' + id);
  }

  updateEvent(params): Observable<any> {
    return this.http.post('/api/events', params);
  }

  getEventByPatientId(id: number): Observable<any> {
    return this.http.get('/api/eventsByPatient' + getQuerySearch({id}));
  }

}
