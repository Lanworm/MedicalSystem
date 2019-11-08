import {Component, Input, OnInit} from '@angular/core';
import * as moment from 'moment';
import {TIME_FORMAT} from '../../constants';
import {EventsService} from '../../shared/events/events.service';

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

  constructor(private eventService: EventsService) {
  }

  getTableData = (dataTablesParameters: any, callback) => {
    this.eventService.getAll().subscribe(resp => {
      callback({
        recordsTotal: 20,
        recordsFiltered: resp.recordsFiltered,
        data: resp
      });
    });
  }

  ngOnInit(): void {

    // @ts-ignore
    $('#eventTableList').DataTable({
      searching: false,
      // serverSide: true,
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
          title: 'From',
          render: (data, type, row) => {
            return moment(row.start_date).format(TIME_FORMAT);
          },
        },
        {
          title: 'To',
          render: (data, type, row) => {
            return moment(row.end_date).format(TIME_FORMAT);
          },
        },
        {
          render: (data, type, row) => {
            return row.patient.firstName + ' ' + row.patient.secondName + ' ' + row.patient.lastName;
          },
          title: 'Patient'
        },
        {
          title: 'Procedure',
          data: 'procedure.description'
        },
        {
          title: 'Room',
          data: 'room.description'
        },
        {
          render: (data, type, row) => {
            return row.staff.firstName + ' ' + row.staff.secondName + ' ' + row.staff.lastName;
          },
          title: 'Doctor'
        },
        {
          data: 'status',
          title: 'Status',
          render: (data, type, row) => {
            return ('<a class="ui ' + this.statusColors[row.status] + ' label">' + row.status + '</a>');
          },
        },
        {
          data: 'status',
          orderable: false,
          className: 'ui center aligned',
          render: () => {
            return ('<i class="edit link icon" edit></i>');
          },
        },
        {
          data: 'status',
          orderable: false,
          className: 'ui center aligned',
          render: () => {
            return ('<i class="trash link icon" delete></i>');
          },
        },
      ]
    });
  }
}
