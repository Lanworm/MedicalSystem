import {Component, Input, OnInit} from '@angular/core';
import {ProcedureService} from '../../services/procedures/procedure.service';
import {DrugService} from '../../services/drug/drug.service';
import {FormControl} from '@angular/forms';
import * as moment from 'moment';

declare var $: any;

@Component({
  selector: 'app-prescription-form',
  templateUrl: './prescription-form.component.html',
  styleUrls: ['./prescription-form.component.css']
})
export class PrescriptionFormComponent implements OnInit {
  @Input() dialogData;
  @Input() closeDialog;
  @Input() showDialog: boolean;
  @Input() submitForm;
  @Input() dialogError;


  constructor(
    private procedureService: ProcedureService,
    private drugService: DrugService
  ) {
  }

  public procedureList: Array<any> = [];
  public drugList: Array<any> = [];

  procedureControl: FormControl;
  drugControl: FormControl;
  freqControl: FormControl;
  dosageControl: FormControl;
  descriptionControl: FormControl;

  fromDate;
  toDate;

  public cronConfig = {
    multiple: true,
    bootstrap: false,
    quartz: true
  };

  onSubmit = () => {
    const params = {
      end_date: +moment(this.toDate),
      start_date: +moment(this.fromDate),
      procedure_id: this.procedureControl.value,
      drug_id: this.drugControl.value,
      time_pattern: this.freqControl.value,
      dosage: this.dosageControl.value,
      description: this.descriptionControl.value
    };
    this.submitForm(params);
  };

  ngOnInit() {

    const nowDate = new Date;
    const {
      procedure: {id: procedureId = null} = {},
      drug: {id: drugId = null} = {},
      time_pattern = '',
      dosage = '',
      start_date = nowDate,
      end_date = nowDate,
      description = ''
    } = this.dialogData || {};
    Promise.all([
      this.procedureService.getAll().toPromise(),
      this.drugService.getAll().toPromise(),
    ]).then(result => {
      this.procedureList = result[0];
      this.drugList = result[1];
    });

    (<any> $('#startDate')).calendar({
      endCalendar: $('#endDate'),
      onChange: (date, text) => {
        this.fromDate = date;
      },
      initialDate: moment(start_date).toDate()
    });
    (<any> $('#endDate')).calendar({
      startCalendar: $('#startDate'),
      onChange: (date, text) => {
        this.toDate = date;
      },
      initialDate: moment(end_date).toDate()
    });

    this.procedureControl = new FormControl(procedureId);
    this.drugControl = new FormControl(drugId);
    this.freqControl = new FormControl(time_pattern);
    this.dosageControl = new FormControl(dosage);
    this.descriptionControl = new FormControl(description);
  }

}
