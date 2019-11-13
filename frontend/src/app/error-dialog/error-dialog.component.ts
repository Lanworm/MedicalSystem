import {Component, inject, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ErrorDialogService} from '../services/error/error-dialog.service';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrls: ['./error-dialog.component.css']
})
export class ErrorDialogComponent implements OnInit, OnChanges {

  public errors = [];

  constructor(public errorDialogService: ErrorDialogService) {

  }

  addError(error) {
    this.errors.push(error);
    setTimeout(() => {
      this.errors.shift();
    }, 5000);
  }

  ngOnInit() {
    this.errorDialogService.getError().subscribe(result => {

      this.addError(result);
    });
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('Updated');
  }

}
