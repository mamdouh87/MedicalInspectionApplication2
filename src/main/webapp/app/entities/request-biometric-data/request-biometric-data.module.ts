import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { RequestBiometricDataComponent } from './request-biometric-data.component';
import { RequestBiometricDataDetailComponent } from './request-biometric-data-detail.component';
import { RequestBiometricDataUpdateComponent } from './request-biometric-data-update.component';
import { RequestBiometricDataDeleteDialogComponent } from './request-biometric-data-delete-dialog.component';
import { requestBiometricDataRoute } from './request-biometric-data.route';

@NgModule({
  imports: [MedicalInspectionApplicationSharedModule, RouterModule.forChild(requestBiometricDataRoute)],
  declarations: [
    RequestBiometricDataComponent,
    RequestBiometricDataDetailComponent,
    RequestBiometricDataUpdateComponent,
    RequestBiometricDataDeleteDialogComponent,
  ],
  entryComponents: [RequestBiometricDataDeleteDialogComponent],
})
export class MedicalInspectionApplicationRequestBiometricDataModule {}
