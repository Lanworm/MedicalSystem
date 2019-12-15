import {Component, Input, OnInit} from '@angular/core';
import {ProcedureService} from '../../services/procedures/procedure.service';
import {DrugService} from '../../services/drug/drug.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
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

  prescriptionForm = new FormGroup({});
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
      procedure_id: this.prescriptionForm.get('procedureControl').value,
      drug_id: this.prescriptionForm.get('drugControl').value,
      time_pattern: this.prescriptionForm.get('freqControl').value,
      dosage: this.prescriptionForm.get('dosageControl').value,
      description: this.prescriptionForm.get('descriptionControl').value
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
      initialDate: moment(start_date).toDate(),
      type: 'date'
    });
    (<any> $('#endDate')).calendar({
      startCalendar: $('#startDate'),
      onChange: (date, text) => {
        this.toDate = date;
      },
      initialDate: moment(end_date).toDate(),
      type: 'date'
    });

    this.prescriptionForm.addControl('procedureControl', new FormControl(procedureId, Validators.required));
    this.prescriptionForm.addControl('drugControl', new FormControl(drugId, Validators.required));
    this.prescriptionForm.addControl('freqControl', new FormControl(time_pattern, Validators.required));
    this.prescriptionForm.addControl('dosageControl', new FormControl(dosage, Validators.required));
    this.prescriptionForm.addControl('descriptionControl', new FormControl(description, Validators.required));
  }

}
