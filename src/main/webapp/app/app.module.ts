import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MedicalInspectionApplicationSharedModule } from 'app/shared/shared.module';
import { MedicalInspectionApplicationCoreModule } from 'app/core/core.module';
import { MedicalInspectionApplicationAppRoutingModule } from './app-routing.module';
import { MedicalInspectionApplicationHomeModule } from './home/home.module';
import { MedicalInspectionApplicationEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    MedicalInspectionApplicationSharedModule,
    MedicalInspectionApplicationCoreModule,
    MedicalInspectionApplicationHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MedicalInspectionApplicationEntityModule,
    MedicalInspectionApplicationAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class MedicalInspectionApplicationAppModule {}
