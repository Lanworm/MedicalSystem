import {Component, Input, OnInit} from '@angular/core';
import * as moment from 'moment';
import {TIME_FORMAT} from '../../constants';

declare var $: any;

@Component({
  selector: 'app-prescriptions-list',
  templateUrl: './prescriptions-list.component.html',
  styleUrls: ['./prescriptions-list.component.css']
})
export class PrescriptionsListComponent implements OnInit {
  @Input() getTableData;
  constructor() { }

  ngOnInit(): void {


    $('#PrescriptionsTableList').DataTable({
      searching: false,
      serverSide: true,
      processing: true,
      lengthChange: false,
      // rowCallback: (row: Node, data: any[]) => {
      //   $('td i[edit]', row).unbind('click');
      //   $('td i[edit]', row).bind('click', () => {
      //     this.openDialog(data);
      //   });
      //   $('td i[delete]', row).unbind('click');
      //   $('td i[delete]', row).bind('click', () => {
      //     this.openDeleteDialog(data);
      //   });
      //   return row;
      // },
      ajax: this.getTableData,
      columns: [
        {
          name: 'From',
          title: 'From',
          render: (data, type, row) => {
            return moment(row.start_date).format(TIME_FORMAT);
          },
        },
        {
          name: 'To',
          title: 'To',
          render: (data, type, row) => {
            return moment(row.end_date).format(TIME_FORMAT);
          },
        },
        {
          name: 'Pattern',
          title: 'Pattern',
          data: 'time_pattern'
        },
        {
          name: 'Procedure',
          title: 'Procedure',
          data: 'procedure.description'
        },
        {
          name: 'Drug',
          title: 'Drug',
          data: 'drug.description'
        },
        {
          name: 'Dosage',
          title: 'Dosage',
          data: 'dosage'
        },
        {
          name: 'Description',
          title: 'Description',
          data: 'description'
        },
      ]
    });
  }
}
