import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInspectionResult } from 'app/shared/model/inspection-result.model';
import { InspectionResultService } from './inspection-result.service';

@Component({
  templateUrl: './inspection-result-delete-dialog.component.html',
})
export class InspectionResultDeleteDialogComponent {
  inspectionResult?: IInspectionResult;

  constructor(
    protected inspectionResultService: InspectionResultService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.inspectionResultService.delete(id).subscribe(() => {
      this.eventManager.broadcast('inspectionResultListModification');
      this.activeModal.close();
    });
  }
}
