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
          name: 'from',
          title: 'From',
          render: (data, type, row) => {
            return moment(row.start_date).format(TIME_FORMAT);
          },
        },
        {
          name: 'to',
          title: 'To',
          render: (data, type, row) => {
            return moment(row.end_date).format(TIME_FORMAT);
          },
        },
        {
          name: 'patient',
          render: (data, type, row) => {
            return (
              '<a href="/card/' + row.patient.id + '">'
              + row.patient.first_name + ' '
              + row.patient.second_name + ' '
              + row.patient.last_name +
              '</a>');
          },
          title: 'Patient'
        },
        {
          name: 'procedure',
          title: 'Procedure',
          data: 'procedure.description'
        },
        {
          name: 'room',
          title: 'Room',
          data: 'room.description'
        },
        {
          name: 'doctor',
          render: (data, type, row) => {
            return row.staff.first_name + ' ' + row.staff.second_name + ' ' + row.staff.last_name;
          },
          title: 'Doctor'
        },

        {
          orderable: false,
          className: 'ui center aligned',
          render: () => {
            return ('<i class="edit link icon" edit></i>');
          },
        },
        {
          orderable: false,
          className: 'ui center aligned',
          // visible: this.authService.userHasRole(USER_ROLES.ROLE_ADMIN),
          render: () => {
            return ('<i class="trash link icon" delete></i>');
          },
        },
      ]
    });
  }
}
