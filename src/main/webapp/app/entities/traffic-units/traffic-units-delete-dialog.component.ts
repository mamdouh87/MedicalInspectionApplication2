import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITrafficUnits } from 'app/shared/model/traffic-units.model';
import { TrafficUnitsService } from './traffic-units.service';

@Component({
  templateUrl: './traffic-units-delete-dialog.component.html',
})
export class TrafficUnitsDeleteDialogComponent {
  trafficUnits?: ITrafficUnits;

  constructor(
    protected trafficUnitsService: TrafficUnitsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.trafficUnitsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('trafficUnitsListModification');
      this.activeModal.close();
    });
  }
}
