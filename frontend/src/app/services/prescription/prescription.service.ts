import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {getQuerySearch} from '../../utils/url';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  constructor(private http: HttpClient) {

  }

  add(params): Observable<any> {
    return this.http.post('/api/prescription', params);
  }

  update(params): Observable<any> {
    return this.http.post('/api/prescription', params);
  }

  getAll(): Observable<any> {
    return this.http.get('/api/prescriptions');
  }

  getById(id: number): Observable<any> {
    return this.http.get('/api/prescriptionsByPatient/' + getQuerySearch({id}));
  }
}
