import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { InspectionTypeComponent } from './inspection-type.component';
import { InspectionTypeDetailComponent } from './inspection-type-detail.component';
import { InspectionTypeUpdateComponent } from './inspection-type-update.component';
import { InspectionTypeDeleteDialogComponent } from './inspection-type-delete-dialog.component';
import { inspectionTypeRoute } from './inspection-type.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(inspectionTypeRoute)],
  declarations: [
    InspectionTypeComponent,
    InspectionTypeDetailComponent,
    InspectionTypeUpdateComponent,
    InspectionTypeDeleteDialogComponent,
  ],
  entryComponents: [InspectionTypeDeleteDialogComponent],
})
export class MedicalInspectionApplicationInspectionTypeModule {}
