import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { LicenseCategoryDetailComponent } from 'app/entities/license-category/license-category-detail.component';
import { LicenseCategory } from 'app/shared/model/license-category.model';

describe('Component Tests', () => {
  describe('LicenseCategory Management Detail Component', () => {
    let comp: LicenseCategoryDetailComponent;
    let fixture: ComponentFixture<LicenseCategoryDetailComponent>;
    const route = ({ data: of({ licenseCategory: new LicenseCategory(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [LicenseCategoryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(LicenseCategoryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LicenseCategoryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load licenseCategory on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.licenseCategory).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
