import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'persons',
        loadChildren: () => import('./persons/persons.module').then(m => m.MedicalInspectionApplicationPersonsModule),
      },
      {
        path: 'medical-insepction-requests',
        loadChildren: () =>
          import('./medical-insepction-requests/medical-insepction-requests.module').then(
            m => m.MedicalInspectionApplicationMedicalInsepctionRequestsModule
          ),
      },
      {
        path: 'request-biometric-data',
        loadChildren: () =>
          import('./request-biometric-data/request-biometric-data.module').then(
            m => m.MedicalInspectionApplicationRequestBiometricDataModule
          ),
      },
      {
        path: 'abdominal-inseption',
        loadChildren: () =>
          import('./abdominal-inseption/abdominal-inseption.module').then(m => m.MedicalInspectionApplicationAbdominalInseptionModule),
      },
      {
        path: 'ophthalmic-inspection',
        loadChildren: () =>
          import('./ophthalmic-inspection/ophthalmic-inspection.module').then(
            m => m.MedicalInspectionApplicationOphthalmicInspectionModule
          ),
      },
      {
        path: 'medical-condition',
        loadChildren: () =>
          import('./medical-condition/medical-condition.module').then(m => m.MedicalInspectionApplicationMedicalConditionModule),
      },
      {
        path: 'request-status',
        loadChildren: () => import('./request-status/request-status.module').then(m => m.MedicalInspectionApplicationRequestStatusModule),
      },
      {
        path: 'inspection-type',
        loadChildren: () =>
          import('./inspection-type/inspection-type.module').then(m => m.MedicalInspectionApplicationInspectionTypeModule),
      },
      {
        path: 'inspection-result',
        loadChildren: () =>
          import('./inspection-result/inspection-result.module').then(m => m.MedicalInspectionApplicationInspectionResultModule),
      },
      {
        path: 'traffic-units',
        loadChildren: () => import('./traffic-units/traffic-units.module').then(m => m.MedicalInspectionApplicationTrafficUnitsModule),
      },
      {
        path: 'country',
        loadChildren: () => import('./country/country.module').then(m => m.MedicalInspectionApplicationCountryModule),
      },
      {
        path: 'transaction-type',
        loadChildren: () =>
          import('./transaction-type/transaction-type.module').then(m => m.MedicalInspectionApplicationTransactionTypeModule),
      },
      {
        path: 'license-category',
        loadChildren: () =>
          import('./license-category/license-category.module').then(m => m.MedicalInspectionApplicationLicenseCategoryModule),
      },
      {
        path: 'inspection-requirement',
        loadChildren: () =>
          import('./inspection-requirement/inspection-requirement.module').then(
            m => m.MedicalInspectionApplicationInspectionRequirementModule
          ),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class MedicalInspectionApplicationEntityModule {}
