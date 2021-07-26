var ASM = Java.type('net.minecraftforge.coremod.api.ASMAPI');
var Opcodes = Java.type('org.objectweb.asm.Opcodes');
var InsnList = Java.type('org.objectweb.asm.tree.InsnList');
var InsnNode = Java.type('org.objectweb.asm.tree.InsnNode');
var VarInsnNode = Java.type('org.objectweb.asm.tree.VarInsnNode');
var MethodInsnNode = Java.type('org.objectweb.asm.tree.MethodInsnNode');

function initializeCoreMod() {
    return {
        'calculate-celestial-angle': {
            'target': {
                'type': 'CLASS',
                'name': 'net/minecraft/world/IDayTimeReader'
            },
            'transformer': function (cn) {
                cn.methods.forEach(function (mn) {
                    if (mn.name === ASM.mapMethod('func_242415_f')) {
                        for (var iterator = mn.instructions.iterator(); iterator.hasNext();) {
                            var node = iterator.next();
                            if (node.getOpcode() === Opcodes.INVOKEVIRTUAL && node.name === ASM.mapMethod("func_236032_b_")) {
                                mn.instructions.insertBefore(node, new VarInsnNode(Opcodes.ALOAD, 0));
                                iterator.set(new MethodInsnNode(Opcodes.INVOKESTATIC, "cloud/lemonslice/teastory/common/handler/AsmHandler", "getSeasonCelestialAngle", "(JLnet/minecraft/world/IDayTimeReader;)F", false));
                                ASM.log("INFO", "Inserted season celestial angle callback", {});
                            }
                        }
                    }
                });
                return cn;
            }
        }
    }
}