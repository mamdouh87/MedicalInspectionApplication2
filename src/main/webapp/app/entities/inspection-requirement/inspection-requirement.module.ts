import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { InspectionRequirementComponent } from './inspection-requirement.component';
import { InspectionRequirementDetailComponent } from './inspection-requirement-detail.component';
import { InspectionRequirementUpdateComponent } from './inspection-requirement-update.component';
import { InspectionRequirementDeleteDialogComponent } from './inspection-requirement-delete-dialog.component';
import { inspectionRequirementRoute } from './inspection-requirement.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(inspectionRequirementRoute)],
  declarations: [
    InspectionRequirementComponent,
    InspectionRequirementDetailComponent,
    InspectionRequirementUpdateComponent,
    InspectionRequirementDeleteDialogComponent,
  ],
  entryComponents: [InspectionRequirementDeleteDialogComponent],
})
export class MedicalInspectionApplicationInspectionRequirementModule {}
