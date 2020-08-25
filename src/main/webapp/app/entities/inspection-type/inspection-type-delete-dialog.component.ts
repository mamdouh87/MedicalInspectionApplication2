import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInspectionType } from 'app/shared/model/inspection-type.model';
import { InspectionTypeService } from './inspection-type.service';

@Component({
  templateUrl: './inspection-type-delete-dialog.component.html',
})
export class InspectionTypeDeleteDialogComponent {
  inspectionType?: IInspectionType;

  constructor(
    protected inspectionTypeService: InspectionTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.inspectionTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('inspectionTypeListModification');
      this.activeModal.close();
    });
  }
}
