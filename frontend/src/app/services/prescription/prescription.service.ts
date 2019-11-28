import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  constructor(private http: HttpClient) {

  }

  getAll(): Observable<any> {
    return this.http.get('/api/prescriptions');
  }

  getById(id: number): Observable<any> {
    return this.http.get('/api/prescription/' + id);
  }
}
