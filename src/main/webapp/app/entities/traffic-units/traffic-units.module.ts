import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { TrafficUnitsComponent } from './traffic-units.component';
import { TrafficUnitsDetailComponent } from './traffic-units-detail.component';
import { TrafficUnitsUpdateComponent } from './traffic-units-update.component';
import { TrafficUnitsDeleteDialogComponent } from './traffic-units-delete-dialog.component';
import { trafficUnitsRoute } from './traffic-units.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(trafficUnitsRoute)],
  declarations: [TrafficUnitsComponent, TrafficUnitsDetailComponent, TrafficUnitsUpdateComponent, TrafficUnitsDeleteDialogComponent],
  entryComponents: [TrafficUnitsDeleteDialogComponent],
})
export class MedicalInspectionApplicationTrafficUnitsModule {}
