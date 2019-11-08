import {Component, OnInit} from '@angular/core';
import {EventsService} from '../shared/events/events.service';

@Component({
  selector: 'app-events',
  templateUrl: './events.component.html',
  styleUrls: ['./events.component.css']
})
export class EventsComponent implements OnInit {
  public showDialog = false;
  public showDeleteDialog = false;
  public dialogData;
  public deleteDialogData;
  public deleteDialogCong = {
    description: 'Are you sure you want to delete the event?',
    icon: 'trash alternate icon',
    title: 'Delete event'
  };

  constructor(private eventService: EventsService) {
  }

  public openDialog = (data) => {
    this.showDialog = true;
    this.dialogData = data;
  };

  public closeDialog = () => {
    this.showDialog = false;
  };

  public openDeleteDialog = (data) => {

    this.showDeleteDialog = true;
    this.deleteDialogData = data;
  };

  public closeDeleteDialog = () => {
    this.showDeleteDialog = false;
  };

  public confirmDeleteDialog = () => {
    const {id} = this.deleteDialogData;
    this.eventService.deleteEvent(id).subscribe(resp => {
      this.closeDeleteDialog();
      const table = $('#eventTableList').DataTable();
      table.ajax.reload();
    }, error => {
      console.error(error);
      this.closeDeleteDialog();
    });
  };

  submitForm = (params) => {
    if (this.dialogData) {
      params.id = this.dialogData.id;
      this.eventService.updateEvent(params).subscribe(() => {
          this.closeDialog();
          const table = $('#eventTableList').DataTable();
          table.ajax.reload();
        }, error => {
          console.error(error);
          this.closeDialog();
        }
      );
    } else {
      this.eventService.addEvent(params).subscribe(() => {
          this.closeDialog();
          const table = $('#eventTableList').DataTable();
          table.ajax.reload();
        }, error => {
          console.error(error);
          this.closeDialog();
        }
      );
    }

  };


  ngOnInit() {
  }

}
