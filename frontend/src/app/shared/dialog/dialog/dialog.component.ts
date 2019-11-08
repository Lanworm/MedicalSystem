import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  @Input() onConfirm;
  @Input() onCancel;
  @Input() icon = 'archive icon';
  @Input() title;
  @Input() description;

  constructor() {
  }

  ngOnInit() {
  }

}
