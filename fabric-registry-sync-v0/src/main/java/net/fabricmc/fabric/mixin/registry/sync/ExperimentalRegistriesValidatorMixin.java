/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.mixin.registry.sync;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.registry.ExperimentalRegistriesValidator;
import net.minecraft.registry.RegistryLoader;

import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

@Mixin(ExperimentalRegistriesValidator.class)
class ExperimentalRegistriesValidatorMixin {
	@Redirect(at = @At(value = "FIELD", target = "Lnet/minecraft/registry/RegistryLoader;DYNAMIC_REGISTRIES:Ljava/util/List;"), method = "method_54839")
	private static List<RegistryLoader.Entry<?>> getDynamicRegistries() {
		// Register cloners for all dynamic registries.
		return DynamicRegistries.getDynamicRegistries();
	}
}
