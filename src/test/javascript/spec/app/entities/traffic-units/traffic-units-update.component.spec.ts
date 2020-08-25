import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { TrafficUnitsUpdateComponent } from 'app/entities/traffic-units/traffic-units-update.component';
import { TrafficUnitsService } from 'app/entities/traffic-units/traffic-units.service';
import { TrafficUnits } from 'app/shared/model/traffic-units.model';

describe('Component Tests', () => {
  describe('TrafficUnits Management Update Component', () => {
    let comp: TrafficUnitsUpdateComponent;
    let fixture: ComponentFixture<TrafficUnitsUpdateComponent>;
    let service: TrafficUnitsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [TrafficUnitsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TrafficUnitsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TrafficUnitsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TrafficUnitsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TrafficUnits(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TrafficUnits();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
