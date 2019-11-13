import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class ErrorDialogService {
  private subject = new Subject();

  setError = (error) => {
    this.subject.next(error);
  };

  getError() {
    return this.subject.asObservable();
  }

}
