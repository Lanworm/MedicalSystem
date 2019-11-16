import {Component, Input, OnInit} from '@angular/core';
import * as moment from 'moment';
import {TIME_FORMAT, USER_ROLES} from '../../constants';
import {EventsService} from '../../services/events/events.service';
import {AuthService} from '../../services/auth/auth.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  @Input() openDialog;
  @Input() update;
  @Input() openDeleteDialog;

  statusColors = {
    DONE: 'green',
    IN_WORK: 'yellow',
    CANCELED: 'red',
    ASSIGNED: 'blue'
  };

  constructor(private eventService: EventsService, private authService: AuthService) {
  }

  getTableData = (dataTablesParameters: any, callback) => {
    const {start, length, order, columns} = dataTablesParameters;
    const orderDir = order[0].dir;
    const orderBy = columns[order[0].column].name;

    this.eventService.getAll({start, length, orderBy, orderDir}).subscribe(resp => {
        callback({
          recordsTotal: resp.records,
          recordsFiltered: resp.records,
          data: resp.list
        });
      }, error => {
        callback({
          recordsTotal: 0,
          recordsFiltered: 0,
          data: []
        });
      }
    );
  };

  ngOnInit(): void {

    // @ts-ignore
    $('#eventTableList').DataTable({
      searching: false,
      serverSide: true,
      processing: true,
      rowCallback: (row: Node, data: any[]) => {
        $('td i[edit]', row).unbind('click');
        $('td i[edit]', row).bind('click', () => {
          this.openDialog(data);
        });
        $('td i[delete]', row).unbind('click');
        $('td i[delete]', row).bind('click', () => {
          this.openDeleteDialog(data);
        });
        return row;
      },
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
            return row.patient.first_name + ' ' + row.patient.second_name + ' ' + row.patient.last_name;
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
          name: 'status',
          data: 'status',
          title: 'Status',
          render: (data, type, row) => {
            return ('<a class="ui ' + this.statusColors[row.status] + ' label">' + row.status + '</a>');
          },
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
          visible: this.authService.userHasRole(USER_ROLES.ROLE_ADMIN),
          render: () => {
            return ('<i class="trash link icon" delete></i>');
          },
        },
      ]
    });
  }
}
