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
          name: 'Date',
          title: 'Date',
          render: (data, type, row) => {
            return moment(row.updated_at).format(TIME_FORMAT);
          },
        },
        {
          name: 'Procedure',
          title: 'Procedure',
          data: 'procedure.description'
        },
        {
          name: 'Drug',
          title: 'drug',
          data: 'drug.description'
        },
        {
          name: 'Dosage',
          title: 'dosage',
          data: 'dosage'
        },
        {
          name: 'description',
          title: 'description',
          data: 'description'
        },
      ]
    });
  }
}
