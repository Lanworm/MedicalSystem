import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {EventsService} from '../../services/events/events.service';
import * as moment from 'moment';
import {STATUS_COLORS} from '../../constants';

declare var $: any;

@Component({
  selector: 'app-action-list',
  templateUrl: './action-list.component.html',
  styleUrls: ['./action-list.component.css']
})
export class ActionListComponent implements OnInit, OnChanges {

  @Input() startDate;
  @Input() endDate;
  @Input() patientId;


  constructor(
    private eventService: EventsService,
  ) {
  }

  public tableOptions;

  public getCellComponent = (row, dateKey) => {
    if (row[dateKey] && row[dateKey].length) {
      return row[dateKey].map((item) => {
        const margin = 100 / 24 * moment(item.start_date).hours();
        const toolTip = moment(item.start_date).format('dddd D MMM HH:mm') + ' ' + item.status + ' ' + item.procedure.description;
        return (
          '<div data-tooltip="' + toolTip + '" style="width: 24px; display: inline; position:absolute; left: ' + margin + '%">' +
          '<i class="heartbeat icon ' + STATUS_COLORS[item.status] + '"></i>' +
          '</div>'
        );
      }).join(' ');
    } else {
      return '';
    }
  };

  public getColumns = () => {
    const dayDiff = this.endDate.diff(this.startDate, 'days');
    const resultColumns = [];
    resultColumns.push(
      {
        title: 'Procedure',
        data: 'name',
        width: 170
      }
    );

    for (let i = 0; i < dayDiff; i++) {
      const dateKey = moment(this.startDate).add(i, 'day').format('MMDDYYYY');
      const dateTitle = moment(this.startDate).add(i, 'day').format('dddd D MMM');
      resultColumns.push({
        title: dateTitle,
        className: 'ui middle aligned',
        render: (data, type, row) => '<div style="text-align: left; position: relative; top:-10px">' + this.getCellComponent(row, dateKey) + '</div>',
      });
    }
    return resultColumns;
  };


  public getTableData = (dataTablesParameters: any, callback) => {
    const dayDiff = this.endDate.diff(this.startDate, 'days');
    this.eventService.getEventByPatientId(this.patientId, +this.startDate.toDate(), +this.endDate.toDate()).subscribe(resp => {
        const defaultRow = {};
        const result = [];

        for (let i = 0; i < dayDiff; i++) {
          const dateKey = moment().add(i, 'day').format('MMDDYYYY');
          defaultRow[dateKey] = null;
        }

        resp.forEach((item) => {
          const procItem = result.find(i => item.procedure.id === i.id);
          const dayKey = moment(item.start_date).format('MMDDYYYY');
          if (!!procItem) {
            if (!Array.isArray(procItem[dayKey])) {
              procItem[dayKey] = [];
            }
            procItem[dayKey].push(item);
          } else {
            result.push(Object.assign({}, defaultRow, {id: item.procedure.id, name: item.procedure.description, [dayKey]: new Array(item)}));
          }
        });

        console.log(result);
        callback({
          recordsTotal: resp.lenght,
          recordsFiltered: resp.lenght,
          data: result
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

    this.tableOptions = {
      columns: this.getColumns(),
      deferRender: true,
      searching: false,
      serverSide: true,
      processing: true,
      lengthChange: false,
      paging: false,
      info: false,
      ajax: this.getTableData,
    };

    $('#eventTable').DataTable(this.tableOptions);
  }

  ngOnChanges(changes) {
    const {startDate, endDate} = changes;
    this.tableOptions = {
      columns: this.getColumns(),
      deferRender: true,
      searching: false,
      serverSide: true,
      processing: true,
      lengthChange: false,
      paging: false,
      info: false,
      ajax: this.getTableData,
    };
    console.log(changes);
    if (startDate && !startDate.firstChange || endDate && !endDate.firstChange) {
      const table = $('#eventTable').DataTable();
      table.destroy();
      $('#eventTable').empty();
      $('#eventTable').DataTable(this.tableOptions);
    }
  }

}
