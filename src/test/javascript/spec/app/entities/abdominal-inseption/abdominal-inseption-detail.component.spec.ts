import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { AbdominalInseptionDetailComponent } from 'app/entities/abdominal-inseption/abdominal-inseption-detail.component';
import { AbdominalInseption } from 'app/shared/model/abdominal-inseption.model';

describe('Component Tests', () => {
  describe('AbdominalInseption Management Detail Component', () => {
    let comp: AbdominalInseptionDetailComponent;
    let fixture: ComponentFixture<AbdominalInseptionDetailComponent>;
    const route = ({ data: of({ abdominalInseption: new AbdominalInseption(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [AbdominalInseptionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(AbdominalInseptionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(AbdominalInseptionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load abdominalInseption on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.abdominalInseption).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
