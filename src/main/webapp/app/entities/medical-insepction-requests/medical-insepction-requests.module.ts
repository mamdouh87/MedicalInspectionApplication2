import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { MedicalInsepctionRequestsComponent } from './medical-insepction-requests.component';
import { MedicalInsepctionRequestsDetailComponent } from './medical-insepction-requests-detail.component';
import { MedicalInsepctionRequestsUpdateComponent } from './medical-insepction-requests-update.component';
import { MedicalInsepctionRequestsDeleteDialogComponent } from './medical-insepction-requests-delete-dialog.component';
import { medicalInsepctionRequestsRoute } from './medical-insepction-requests.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(medicalInsepctionRequestsRoute)],
  declarations: [
    MedicalInsepctionRequestsComponent,
    MedicalInsepctionRequestsDetailComponent,
    MedicalInsepctionRequestsUpdateComponent,
    MedicalInsepctionRequestsDeleteDialogComponent,
  ],
  entryComponents: [MedicalInsepctionRequestsDeleteDialogComponent],
})
export class MedicalInspectionApplicationMedicalInsepctionRequestsModule {}
