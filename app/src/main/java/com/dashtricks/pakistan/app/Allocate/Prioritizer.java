package com.dashtricks.pakistan.app.allocate;

import com.dashtricks.pakistan.app.model.VolumeRequirement;
import com.dashtricks.pakistan.app.model.VolumeRequirementBuilder;

import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public interface Prioritizer {
    VolumeRequirementBuilder remove();

    void add(VolumeRequirementBuilder current);

    Set<VolumeRequirement> done();
}
