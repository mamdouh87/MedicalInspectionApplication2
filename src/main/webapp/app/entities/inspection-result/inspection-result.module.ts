import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { InspectionResultComponent } from './inspection-result.component';
import { InspectionResultDetailComponent } from './inspection-result-detail.component';
import { InspectionResultUpdateComponent } from './inspection-result-update.component';
import { InspectionResultDeleteDialogComponent } from './inspection-result-delete-dialog.component';
import { inspectionResultRoute } from './inspection-result.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(inspectionResultRoute)],
  declarations: [
    InspectionResultComponent,
    InspectionResultDetailComponent,
    InspectionResultUpdateComponent,
    InspectionResultDeleteDialogComponent,
  ],
  entryComponents: [InspectionResultDeleteDialogComponent],
})
export class MedicalInspectionApplicationInspectionResultModule {}
