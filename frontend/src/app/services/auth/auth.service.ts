import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private expiresAt: number = null;

  constructor(private http: HttpClient) {
    const expiresAt = localStorage.getItem('jwt_expires_at');
    if (expiresAt) {
      this.expiresAt = Number(expiresAt);
    }
  }

  public auth(username: string, password: string): Observable<any> {
    const body = new HttpParams()
      .append('username', username)
      .append('password', password);
    return this.http.post('/api/authenticate', body).pipe(tap(response => {
      this.expiresAt = response.expiresAt;
      localStorage.setItem('jwt_expires_at', String(this.expiresAt));
    }));
  }

  public logout(): Observable<any> {
    return this.http.post('/api/logout', {}).pipe(tap(response => {
      localStorage.removeItem('jwt_expires_at');
      this.expiresAt = null;
    }));
  }

  public isAuthenticated(): boolean {
    return this.expiresAt != null && (new Date().getTime() <= this.expiresAt);
  }
}
