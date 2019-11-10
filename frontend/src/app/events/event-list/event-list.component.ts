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
    console.log(dataTablesParameters);
    const {start, length, order, columns} = dataTablesParameters;
    const orderDir = order[0].dir;
    const orderBy = columns[order[0].column].name;

    this.eventService.getAll({start, length, orderBy, orderDir}).subscribe(resp => {
      callback({
        recordsTotal: resp.records,
        recordsFiltered: resp.records,
        data: resp.list
      });
    });
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
          name: 'startDate',
          title: 'From',
          render: (data, type, row) => {
            return moment(row.start_date).format(TIME_FORMAT);
          },
        },
        {
          name: 'endDate',
          title: 'To',
          render: (data, type, row) => {
            return moment(row.end_date).format(TIME_FORMAT);
          },
        },
        {
          name: 'patientsByPatientId',
          render: (data, type, row) => {
            return row.patient.first_name + ' ' + row.patient.second_name + ' ' + row.patient.last_name;
          },
          title: 'Patient'
        },
        {
          name: 'procedure_id',
          title: 'Procedure',
          data: 'procedure.description'
        },
        {
          name: 'room_id',
          title: 'Room',
          data: 'room.description'
        },
        {
          name: 'staff_id',
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
          render: () => {
            return ('<i class="trash link icon" delete></i>');
          },
        },
      ]
    });
  }
}
