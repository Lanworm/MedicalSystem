import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  constructor(private http: HttpClient) {

  }

  getAll(): Observable<any> {
    return this.http.get('/api/patients');
  }

  getById(id: number): Observable<any> {
    return this.http.get('/api/patient/' + id);
  }
}
