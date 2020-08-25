import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInspectionRequirement } from 'app/shared/model/inspection-requirement.model';
import { InspectionRequirementService } from './inspection-requirement.service';

@Component({
  templateUrl: './inspection-requirement-delete-dialog.component.html',
})
export class InspectionRequirementDeleteDialogComponent {
  inspectionRequirement?: IInspectionRequirement;

  constructor(
    protected inspectionRequirementService: InspectionRequirementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.inspectionRequirementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('inspectionRequirementListModification');
      this.activeModal.close();
    });
  }
}
